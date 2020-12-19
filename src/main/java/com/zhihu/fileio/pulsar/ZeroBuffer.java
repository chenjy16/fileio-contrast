package com.zhihu.fileio.pulsar;

import java.nio.ByteBuffer;

public class ZeroBuffer {
    private static final byte[] zeroBytes = new byte[64 * 1024];

    /**
     * Fill zeros into given buffer.
     * @param dst
     */
    public static void put(ByteBuffer dst) {
        put(dst, dst.remaining());
    }

    /**
     * Fill zeros into given buffer up to given length.
     * @param dst
     * @param length
     */
    public static void put(ByteBuffer dst, int length) {
        while (length > zeroBytes.length) {
            dst.put(zeroBytes);
            length -= zeroBytes.length;
        }
        if (length > 0) {
            dst.put(zeroBytes, 0, length);
        }
    }

    /**
     * Returns read-only zero-filled buffer.
     * @param length
     * @return ByteBuffer
     */
    public static ByteBuffer readOnlyBuffer(int length) {
        ByteBuffer buffer;
        if (length <= zeroBytes.length) {
            buffer = ByteBuffer.wrap(zeroBytes, 0, length);
        } else {
            buffer = ByteBuffer.allocate(length);
            put(buffer);
            buffer.rewind();
        }
        return buffer.asReadOnlyBuffer();
    }
}
