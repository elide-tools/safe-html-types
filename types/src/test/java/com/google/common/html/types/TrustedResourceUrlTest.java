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

import static com.google.common.html.types.testing.HtmlConversions.newTrustedResourceUrlForTest;

import com.google.common.annotations.GwtCompatible;
import com.google.common.testing.EqualsTester;
import junit.framework.TestCase;

/** Unit tests for {@link TrustedResourceUrl}. */
@GwtCompatible
public class TrustedResourceUrlTest extends TestCase {

  // TODO(mlourenco): Remove usage newTrustedResourceUrlForTest once we have a GWT
  // version of builders.

  public void testToString_returnsDebugString() {
    assertEquals("TrustedResourceUrl{url}", newTrustedResourceUrlForTest("url").toString());
  }

  public void testEqualsAndHashCode() {
    new EqualsTester()
        .addEqualityGroup(
            newTrustedResourceUrlForTest("url1"), newTrustedResourceUrlForTest("url1"))
        .addEqualityGroup(
            newTrustedResourceUrlForTest("url2"), newTrustedResourceUrlForTest("url2"))
        .testEquals();
  }
}
