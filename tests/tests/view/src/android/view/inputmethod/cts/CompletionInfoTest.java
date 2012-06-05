/*
 * Copyright (C) 2009 The Android Open Source Project
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

package android.view.inputmethod.cts;

import dalvik.annotation.TestLevel;
import dalvik.annotation.TestTargetClass;
import dalvik.annotation.TestTargetNew;
import dalvik.annotation.TestTargets;

import android.os.Parcel;
import android.test.AndroidTestCase;
import android.view.inputmethod.CompletionInfo;

@TestTargetClass(CompletionInfo.class)
public class CompletionInfoTest extends AndroidTestCase {
    private static final int ID = 1;
    private static final int POSITION = 1;
    private static final String TEXT = "CompletionInfoText";
    private static final String LABEL = "CompletionInfoLabel";

    @TestTargets({
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "describeContents",
            args = {}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "writeToParcel",
            args = {Parcel.class, int.class}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "CompletionInfo",
            args = {long.class, int.class, CharSequence.class, CharSequence.class}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "CompletionInfo",
            args = {long.class, int.class, CharSequence.class}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "getId",
            args = {}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "getPosition",
            args = {}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "getText",
            args = {}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "getLabel",
            args = {}
        ),
        @TestTargetNew(
            level = TestLevel.COMPLETE,
            method = "toString",
            args = {}
        )
    })
    public void testCompletionInfo() {
        new CompletionInfo(ID, POSITION, TEXT);
        CompletionInfo info = new CompletionInfo(ID, POSITION, TEXT, LABEL);
        assertCompletionInfo(info);

        assertEquals(0, info.describeContents());
        assertNotNull(info.toString());

        Parcel p = Parcel.obtain();
        info.writeToParcel(p, 0);
        p.setDataPosition(0);
        CompletionInfo targetInfo = CompletionInfo.CREATOR.createFromParcel(p);
        p.recycle();
        assertCompletionInfo(targetInfo);
    }

    private void assertCompletionInfo(CompletionInfo info) {
        assertEquals(ID, info.getId());
        assertEquals(POSITION, info.getPosition());
        assertEquals(TEXT, info.getText().toString());
        assertEquals(LABEL, info.getLabel().toString());
    }
}
