package com.zhihu.fileio.jraft;

import com.sun.jna.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

public class Utils {

    private static final Logger LOG                                 = LoggerFactory.getLogger(Utils.class);



    /**
     * ANY IP address 0.0.0.0
     */
    public static final String IP_ANY = "0.0.0.0";

    /**
     * Gets the current monotonic time in milliseconds.
     */
    public static long monotonicMs() {
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }

    /**
     * Returns the current time in milliseconds, it's not monotonic, would be forwarded/backward by
     * clock synchronous.
     */
    public static long nowMs() {
        return System.currentTimeMillis();
    }

    /**
     * Gets the current monotonic time in microseconds.
     */
    public static long monotonicUs() {
        return TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
    }

    /**
     * Get string bytes in UTF-8 charset.
     */
    public static byte[] getBytes(final String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Calls fsync on a file or directory.
     * @param file file or directory
     * @throws IOException if an I/O error occurs
     */
    public static void fsync(final File file) throws IOException {
        final boolean isDir = file.isDirectory();
        // can't fsync on windowns.
        if (isDir && Platform.isWindows()) {
            LOG.warn("Unable to fsync directory {} on windows.", file);
            return;
        }
        try (final FileChannel fc = FileChannel.open(file.toPath(), isDir ? StandardOpenOption.READ
                : StandardOpenOption.WRITE)) {
            fc.force(true);
        }
    }

    public static String getString(final byte[] bs, final int off, final int len) {
        return new String(bs, off, len, StandardCharsets.UTF_8);
    }
}
