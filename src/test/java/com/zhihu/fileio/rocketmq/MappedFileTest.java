package com.zhihu.fileio.rocketmq;

import org.junit.After;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;


public class MappedFileTest {
    private final String storeMessage = "Once, there was a chance for me!";

    @Test
    public void testSelectMappedBuffer() throws IOException {
        MappedFile mappedFile = new MappedFile("target/unit_test_store/MappedFileTest/000", 1024 * 64);
        boolean result = mappedFile.appendMessage(storeMessage.getBytes());
        assertThat(result).isTrue();

        SelectMappedBufferResult selectMappedBufferResult = mappedFile.selectMappedBuffer(0);
        byte[] data = new byte[storeMessage.length()];
        selectMappedBufferResult.getByteBuffer().get(data);
        String readString = new String(data);

        assertThat(readString).isEqualTo(storeMessage);

        mappedFile.shutdown(1000);
        assertThat(mappedFile.isAvailable()).isFalse();
        selectMappedBufferResult.release();
        assertThat(mappedFile.isCleanupOver()).isTrue();
        assertThat(mappedFile.destroy(1000)).isTrue();
    }

    @After
    public void destory() {
        File file = new File("target/unit_test_store");
        //UtilAll.deleteFile(file);
    }
}

