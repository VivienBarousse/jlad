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
package com.aperigeek.jlad.detector.ngrams;

/**
 *
 * @author Vivien Barousse
 */
public class Language {
    
    private String languageCode;
    
    private NGramsContainer ngrams;

    public Language(String languageCode) {
        this(languageCode, new NGramsContainer());
    }

    public Language(String languageCode, NGramsContainer ngrams) {
        this.languageCode = languageCode;
        this.ngrams = ngrams;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public NGramsContainer getNgrams() {
        return ngrams;
    }
    
}
