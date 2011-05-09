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
package com.aperigeek.jlad.core.ngram;

import java.util.HashMap;
import java.util.Map;

/**
 * Collects n-grams from given words.
 * 
 * N-grams are extracted and counted for each word given to the collect()
 * method.
 * 
 * Currently, only 2-grams, 3-grams and 4-grams are counted.
 * 
 * @author Vivien Barousse
 */
public class NGramsCollector {

    private Map<String, Integer> ngramsCount;

    public NGramsCollector() {
        ngramsCount = new HashMap<String, Integer>();
    }

    public void collect(String word) {
        for (String ngram : NGramsUtils.ngramify(word)) {
            add(ngram);
        }
    }

    private void add(String ngram) {
        if (ngramsCount.containsKey(ngram)) {
            ngramsCount.put(ngram, ngramsCount.get(ngram) + 1);
        } else {
            ngramsCount.put(ngram, 1);
        }
    }

    public Map<String, Integer> getNgramsCount() {
        return ngramsCount;
    }
}
