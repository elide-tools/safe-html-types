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

import javax.annotation.CheckReturnValue;
import javax.annotation.concurrent.Immutable;
import jsinterop.annotations.JsType;

/**
 * A string that is safe to use in URL context in DOM APIs and HTML documents.
 *
 * <p>A SafeUrl is a string-like object that carries the security type contract that its value as a
 * string will not cause untrusted script execution when evaluated as a hyperlink URL in a browser.
 *
 * <p>Values of this type are guaranteed to be safe to use in URL/hyperlink contexts, such as
 * assignment to URL-valued DOM properties, in the sense that the use will not result in a
 * Cross-Site-Scripting vulnerability. Similarly, SafeUrls can be interpolated into the URL context
 * of an HTML template (e.g., inside a href attribute). However, appropriate HTML-escaping must
 * still be applied.
 *
 * <p>Note that this type's contract does not imply any guarantees regarding the resource the URL
 * refers to. In particular, SafeUrls are <b>not</b> safe to use in a context where the referred-to
 * resource is interpreted as trusted code, e.g., as the src of a script tag.
 */
@CheckReturnValue
@Immutable
@JsType
public final class SafeUrl {

  /**
   * The innocuous string generated by
   * {@link com.google.common.html.types.SafeUrls#sanitize(String)} (or
   * {@link com.google.common.html.types.portable.builders.SafeUrls#sanitize(String)}) when passed
   * an unsafe URL.
   *
   * about:invalid is registered in http://www.w3.org/TR/css3-values/#about-invalid.
   * http://tools.ietf.org/html/rfc6694#section-2.1 permits about URLs to contain a fragment,
   * which is not to be considered when determining if an about URL is well-known.
   */
   // Using about:invalid seems preferable to using a fixed data URL, since browsers might choose
   // to not report CSP violations on it, as legitimate CSS function calls to attr() can result in
   // this URL being produced. It is also a standard URL which matches exactly the semantics we
   // need:
   // "The about:invalid URI references a non-existent document with a generic error condition. It
   // can be used when a URI is necessary, but the default value shouldn't be resolveable as any
   // type of document".
  public static final String INNOCUOUS_STRING = "about:invalid#zGuavaz";

  /**
   * The SafeUrl generated by {@link com.google.common.html.types.SafeUrls#sanitize(String)} (or
   * {@link com.google.common.html.types.portable.builders.SafeUrls#sanitize(String)}) when passed
   * an unsafe URL. Wraps {@link #INNOCUOUS_STRING}.
   */
  public static final SafeUrl INNOCUOUS = new SafeUrl(INNOCUOUS_STRING);

  private final String privateDoNotAccessOrElseSafeUrlWrappedValue;

  SafeUrl(String url) {
    if (url == null) {
      throw new NullPointerException();
    }
    privateDoNotAccessOrElseSafeUrlWrappedValue = url;
  }

  @Override
  public int hashCode() {
    return privateDoNotAccessOrElseSafeUrlWrappedValue.hashCode() ^ 0x01170ef8;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof SafeUrl)) {
      return false;
    }
    SafeUrl that = (SafeUrl) other;
    return this.privateDoNotAccessOrElseSafeUrlWrappedValue.equals(
        that.privateDoNotAccessOrElseSafeUrlWrappedValue);
  }

  /**
   * Returns a debug representation of this value's underlying string, NOT the string representation
   * of the URL.
   *
   * <p>Having {@code toString()} return a debug representation is intentional. This type has
   * a GWT-compiled JavaScript version; JavaScript has no static typing and a distinct method
   * method name provides a modicum of type-safety.
   *
   * @see #getSafeUrlString
   */
  @Override
  public String toString() {
    return "SafeUrl{" + privateDoNotAccessOrElseSafeUrlWrappedValue + "}";
  }

  /**
   * Returns this value's underlying string. See class documentation for what guarantees exist on
   * the returned string.
   */
  // NOTE(mlourenco): jslayout depends on this exact method name when generating code, be careful if
  // changing it.
  public String getSafeUrlString() {
    return privateDoNotAccessOrElseSafeUrlWrappedValue;
  }
}
