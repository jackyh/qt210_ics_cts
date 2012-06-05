/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.net.http.cts;

import android.net.http.SslCertificate;
import android.net.http.SslError;

import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTargetNew;
import dalvik.annotation.TestTargetClass;

import java.util.Date;

import junit.framework.TestCase;

@TestTargetClass(SslError.class)
public class SslErrorTest extends TestCase {
    private SslCertificate mCertificate;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCertificate = new SslCertificate("foo", "bar", new Date(42), new Date(43));
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "hasError",
        args = {int.class}
    )
    public void testHasError() {
        SslError error = new SslError(SslError.SSL_EXPIRED, mCertificate);
        assertTrue(error.hasError(SslError.SSL_EXPIRED));
        assertFalse(error.hasError(SslError.SSL_UNTRUSTED));
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "addError",
        args = {int.class}
    )
    public void testAddError() {
        SslError error = new SslError(SslError.SSL_EXPIRED, mCertificate);
        assertFalse(error.hasError(SslError.SSL_UNTRUSTED));
        error.addError(SslError.SSL_UNTRUSTED);
        assertTrue(error.hasError(SslError.SSL_UNTRUSTED));
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "addError",
        args = {int.class}
    )
    public void testAddErrorIgnoresInvalidValues() {
        SslError error = new SslError(SslError.SSL_EXPIRED, mCertificate);
        error.addError(42);
        assertFalse(error.hasError(42));
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "SslError",
        args = {int.class, SslCertificate.class}
    )
    public void testConstructorIgnoresInvalidValues() {
        SslError error = new SslError(42, mCertificate);
        assertFalse(error.hasError(42));
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "getPrimaryError",
        args = {}
    )
    public void testGetPrimaryError() {
        SslError error = new SslError(SslError.SSL_EXPIRED, mCertificate);
        error.addError(SslError.SSL_UNTRUSTED);
        assertEquals(error.getPrimaryError(), SslError.SSL_UNTRUSTED);
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "getPrimaryError",
        args = {}
    )
    public void testGetPrimaryErrorWithEmptySet() {
        SslError error = new SslError(42, mCertificate);
        assertEquals(error.getPrimaryError(), -1);
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "getUrl",
        args = {}
    )
    public void testGetUrl() {
        SslError error = new SslError(SslError.SSL_EXPIRED, mCertificate, "foo");
        assertEquals(error.getUrl(), "foo");
    }

    @TestTargetNew(
        level = TestLevel.COMPLETE,
        method = "getUrl",
        args = {}
    )
    public void testGetUrlWithDeprecatedConstructor() {
        SslError error = new SslError(SslError.SSL_EXPIRED, mCertificate);
        assertEquals(error.getUrl(), "");
    }
}
