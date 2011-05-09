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
package com.aperigeek.jlad.core.token;

import java.io.IOException;
import java.io.Reader;

/**
 * Splits the content of a Reader into words.
 * 
 * Words are defined as a consecutive suite of letters, and are separated by
 * non-letters characters.
 * 
 * The definition of a letter is the same as Character.isLetter(int).
 * 
 * @author Vivien Barousse
 */
public class WordTokenizer {

    private Reader reader;

    public WordTokenizer(Reader reader) {
        this.reader = reader;
    }

    public String nextWord() throws IOException {
        int ch = reader.read();
        while (ch != -1 && !Character.isLetter(ch)) {
            ch = reader.read();
        }

        if (ch == -1) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        while (ch != -1 && Character.isLetter(ch)) {
            builder.append((char) ch);
            ch = reader.read();
        }
        return builder.toString();
    }
}