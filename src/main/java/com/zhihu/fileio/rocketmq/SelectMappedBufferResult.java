package com.zhihu.fileio.rocketmq;

import java.nio.ByteBuffer;

public class SelectMappedBufferResult {

    private final long startOffset;

    private final ByteBuffer byteBuffer;

    private int size;

    private MappedFile mappedFile;

    public SelectMappedBufferResult(long startOffset, ByteBuffer byteBuffer, int size, MappedFile mappedFile) {
        this.startOffset = startOffset;
        this.byteBuffer = byteBuffer;
        this.size = size;
        this.mappedFile = mappedFile;
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int s) {
        this.size = s;
        this.byteBuffer.limit(this.size);
    }


    public synchronized void release() {
        if (this.mappedFile != null) {
            this.mappedFile.release();
            this.mappedFile = null;
        }
    }

    public long getStartOffset() {
        return startOffset;
    }
}
