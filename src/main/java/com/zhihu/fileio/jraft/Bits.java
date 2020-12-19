package com.zhihu.fileio.jraft;

public class Bits {

    public static int getInt(final byte[] b, final int off) {
        return HeapByteBufUtil.getInt(b, off);
    }

    public static long getLong(final byte[] b, final int off) {
        return HeapByteBufUtil.getLong(b, off);
    }

    public static void putInt(final byte[] b, final int off, final int val) {
        HeapByteBufUtil.setInt(b, off, val);
    }

    public static void putShort(final byte[] b, final int off, final short val) {
        HeapByteBufUtil.setShort(b, off, val);
    }

    public static short getShort(final byte[] b, final int off) {
        return HeapByteBufUtil.getShort(b, off);
    }

    public static void putLong(final byte[] b, final int off, final long val) {
        HeapByteBufUtil.setLong(b, off, val);
    }
}

