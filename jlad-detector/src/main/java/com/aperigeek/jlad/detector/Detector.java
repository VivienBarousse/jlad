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
package com.aperigeek.jlad.detector;

import com.aperigeek.jlad.core.ngram.NGramsUtils;
import com.aperigeek.jlad.core.token.WordTokenizer;
import com.aperigeek.jlad.detector.ngrams.Language;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Detect the language of a piece of text using a Naive Bayes classifier.
 * 
 * @author Vivien Barousse
 */
public class Detector {
    
    private List<Language> languages = new ArrayList<Language>();

    public Detector() {
    }
    
    public void addLanguage(Language language) {
        languages.add(language);
    }
    
    public String detect(String text) {
        try {
            double[] probabilities = new double[languages.size()];
            Arrays.fill(probabilities, 10000);

            WordTokenizer tokenizer = new WordTokenizer(new StringReader(text));
            String word;
            while ((word = tokenizer.nextWord()) != null) {
                String[] ngrams = NGramsUtils.ngramify(word);
                for (String ngram : ngrams) {
                    double ngramCount = 0;
                    for (Language language : languages) {
                        ngramCount += language.getNgrams().getNGram(ngram);
                    }
                    for (int i = 0; i < languages.size(); i++) {
                        if (languages.get(i).getNgrams().getNGram(ngram) != 0) {
                            probabilities[i] *=
                                    languages.get(i).getNgrams().getNGram(ngram) /
                                    ngramCount;
                        }
                    }
                }
            }
            
            double maxProb = probabilities[0];
            int maxProbIdx = 0;
            for (int i = 1; i < probabilities.length; i++) {
                if (probabilities[i] > maxProb) {
                    maxProb = probabilities[i];
                    maxProbIdx = i;
                }
            }
            
            return languages.get(maxProbIdx).getLanguageCode();
        } catch (IOException ex) {
            // Reading from a String, should not happen
            throw new RuntimeException("Unexpected internal IO error", ex);
        }
    }
    
}
