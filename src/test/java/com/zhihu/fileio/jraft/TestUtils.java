/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhihu.fileio.jraft;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Test helper
 *
 * @author boyan (boyan@alibaba-inc.com)
 *
 *         2018-Apr-11 10:16:07 AM
 */
public class TestUtils {


    public static void dumpThreads() {
        try {
            ThreadMXBean bean = ManagementFactory.getThreadMXBean();
            ThreadInfo[] infos = bean.dumpAllThreads(true, true);
            for (ThreadInfo info : infos) {
                System.out.println(info);
            }
        } catch (Throwable t) {
            t.printStackTrace(); // NOPMD
        }
    }

    public static String mkTempDir() {
        //return Paths.get(System.getProperty("java.io.tmpdir", "/tmp"), "jraft_test_" + System.nanoTime()).toString();
        return Paths.get(System.getProperty("java.io.tmpdir", "/tmp") ,"jraft_test_").toString();
    }


    public static byte[] getRandomBytes() {
        final byte[] requestContext = new byte[ThreadLocalRandom.current().nextInt(10) + 1];
        ThreadLocalRandom.current().nextBytes(requestContext);
        return requestContext;
    }
}
