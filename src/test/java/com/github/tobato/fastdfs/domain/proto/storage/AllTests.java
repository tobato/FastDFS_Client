package com.github.tobato.fastdfs.domain.proto.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StorageAppendFileCommandTest.class, StorageDeleteFileCommandTest.class,
        StorageQueryFileInfoCommandTest.class, StorageUploadFileCommandTest.class,
        StorageUploadSlaveFileCommandTest.class, StorageSetMetadataCommandTest.class,
        StorageDownloadCommandTest.class })
public class AllTests {
    // for test
}
