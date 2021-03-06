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
package com.aperigeek.jlad.web.rest;

import com.aperigeek.jlad.web.detector.DetectorService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST endpoint for the detection service
 * 
 * @author Vivien Barousse
 */
@Path("/detect")
@Stateless
public class DetectorRestService {
    
    @EJB
    private DetectorService detector;
    
    @POST
    @Consumes("text/plain")
    @Produces("text/plain")
    public String detect(String text) {
        return detector.detectLanguage(text);
    }
    
}
