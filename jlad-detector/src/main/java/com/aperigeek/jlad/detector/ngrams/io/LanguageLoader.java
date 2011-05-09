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
package com.aperigeek.jlad.detector.ngrams.io;

import com.aperigeek.jlad.detector.ngrams.Language;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vivien Barousse
 */
public class LanguageLoader {
    
    private static final Pattern csvPattern = Pattern.compile("^([^,]{2,4}),([0-9]+)$");
    
    private Reader in;
    
    private Language loaded;

    public LanguageLoader(String languageCode, Reader in) {
        this.in = in;
        loaded = new Language(languageCode);
    }
    
    public void load() throws IOException {
        LineNumberReader reader = new LineNumberReader(in);
        String readed;
        while ((readed = reader.readLine()) != null) {
            // Skip eventual empty lines
            if (readed.trim().isEmpty()) {
                continue;
            }
            
            Matcher matcher = csvPattern.matcher(readed);
            if (!matcher.matches()) {
                throw new IOException("Invalid CSV line " + reader.getLineNumber());
            }
            loaded.getNgrams().put(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }
    }

    public Language getLoadedLanguage() {
        return loaded;
    }
    
}