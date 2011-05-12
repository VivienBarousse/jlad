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
package com.aperigeek.jlad.web.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Vivien Barousse
 */
@Singleton
@Startup
public class LanguagesConfig {
    
    public static final String CONFIG_LOCATION = "/config/languages.properties";

    private Properties properties;
    
    @PostConstruct
    protected void init() {
        properties = new Properties();
        
        try {
            InputStream in = this.getClass().getResourceAsStream(CONFIG_LOCATION);
            properties.load(in);
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Error while loading languages config", 
                    ex);
        }
    }
    
    public String[] getLanguages() {
        String langs = properties.getProperty("jlad.languages", "");
        String[] langsArray = langs.split(",");
        for (int i = 0; i < langsArray.length; i++) {
            langsArray[i] = langsArray[i].trim();
        }
        return langsArray;
    }
    
    public File getLanguageFile(String language) {
        String prop = "jlad.language." + language + ".database";
        String db = properties.getProperty(prop);
        return new File(db);
    }
    
}
