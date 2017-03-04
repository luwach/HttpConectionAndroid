package oo.max.httpexamples;

import java.util.List;

import oo.max.httpexamples.model.TestEntry;

public interface TestCollectionDownloadListener {
    void onDownloaded(List<TestEntry> entries);
}
