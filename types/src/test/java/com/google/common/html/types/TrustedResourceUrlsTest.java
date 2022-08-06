/*
 * Copyright 2016 Google Inc. All Rights Reserved.
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
package com.google.common.html.types;

import static com.google.common.html.types.testing.assertions.Assertions.assertClassIsNotExportable;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import junit.framework.TestCase;

/** Unit tests for {@link TrustedResourceUrls}. */
@GwtCompatible(emulated = true)
public class TrustedResourceUrlsTest extends TestCase {

  @GwtIncompatible("Assertions.assertClassIsNotExportable")
  public void testClassNotExportable() {
    assertClassIsNotExportable(TrustedResourceUrls.class);
  }

  public void testToAndFromProto() {
    final String url = "http://www.google.com";
    TrustedResourceUrl trustedResourceUrl = TrustedResourceUrls.fromConstant(url);
    TrustedResourceUrlProto proto = TrustedResourceUrls.toProto(trustedResourceUrl);
    assertEquals(url, TrustedResourceUrls.fromProto(proto).getTrustedResourceUrlString());
  }

  @GwtIncompatible("System.getenv")
  public void testFromEnvironmentVariable() {
    // Environment variable might exist so we don't check return value. Check that call doesn't
    // throw and that runs on all enviroments the code is transpiled to.
    @SuppressWarnings("unused")
    TrustedResourceUrl url =
        TrustedResourceUrls.fromEnvironmentVariable("somevaluethatprobablydoesntexist");
  }
}
