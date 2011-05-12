/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aperigeek.jlad.web.detector;

import com.aperigeek.jlad.detector.Detector;
import com.aperigeek.jlad.detector.ngrams.io.LanguageLoader;
import com.aperigeek.jlad.web.config.LanguagesConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Vivien Barousse
 */
@Singleton
@Startup
public class DetectorProvider {
    
    private static final Logger log = Logger.getLogger(DetectorProvider.class.getName());
    
    @EJB
    private LanguagesConfig langConf;
    
    private Detector detector;
    
    @PostConstruct
    protected void init() {
        detector = new Detector();
        
        for (String lang : langConf.getLanguages()) {
            try {
                File file = langConf.getLanguageFile(lang);
                FileInputStream in = new FileInputStream(file);
                LanguageLoader loader = new LanguageLoader(lang, in);
                loader.load();
                detector.addLanguage(loader.getLoadedLanguage());
            } catch (IOException ex) {
                log.log(Level.WARNING, 
                        "Error while loading language " + lang + ". Language has been disabled.", 
                        ex);
            }
        }
    }

    public Detector getDetector() {
        return detector;
    }
    
}
