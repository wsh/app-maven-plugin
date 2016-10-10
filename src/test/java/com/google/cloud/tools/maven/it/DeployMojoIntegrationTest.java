/*
 * Copyright (C) 2016 Google Inc.
 *
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

package com.google.cloud.tools.maven.it;

import com.google.cloud.tools.appengine.cloudsdk.internal.process.ProcessRunnerException;
import com.google.cloud.tools.maven.it.verifier.FlexibleVerifier;
import com.google.cloud.tools.maven.it.verifier.StandardVerifier;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Test;

import java.io.IOException;

public class DeployMojoIntegrationTest extends AbstractMojoIntegrationTest {

  @Test
  public void testDeployStandard()
      throws IOException, VerificationException, ProcessRunnerException {

    Verifier verifier = new StandardVerifier("testDeployStandard");

    // execute with staging directory not present
    verifier.executeGoal("appengine:deploy");

    // verify
    verifier.verifyErrorFreeLog();
    verifier.verifyTextInLog("Detected App Engine standard environment application");
    verifier.verifyTextInLog("GCLOUD: Deployed service");

    // cleanup
    deleteService("standard-project");
  }

  @Test
  public void testDeployFlexible()
      throws IOException, VerificationException, ProcessRunnerException {

    Verifier verifier = new FlexibleVerifier("testDeployFlexible");

    // execute with staging directory not present
    verifier.executeGoal("appengine:deploy");

    // verify
    verifier.verifyErrorFreeLog();
    verifier.verifyTextInLog("Detected App Engine flexible environment application");
    verifier.verifyTextInLog("GCLOUD: Deployed service");

    // cleanup
    deleteService("flexible-project");
  }
}
