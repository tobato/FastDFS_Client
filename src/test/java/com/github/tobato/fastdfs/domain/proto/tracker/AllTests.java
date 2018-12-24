package com.github.tobato.fastdfs.domain.proto.tracker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TrackerGetFetchStorageCommandTest.class, TrackerGetStoreStorageCommandTest.class,
        TrackerListGroupsCommandTest.class, TrackerListGroupsRequestTest.class, TrackerListStoragesCommandTest.class })
public class AllTests {
    // for test
}
