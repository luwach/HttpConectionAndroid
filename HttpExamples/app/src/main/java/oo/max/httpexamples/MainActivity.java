package oo.max.httpexamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import oo.max.httpexamples.model.TestEntry;

public class MainActivity extends AppCompatActivity
        implements GetTestCollectionAsyncTask.TestCollectionDownloadListener,
        CreateTestEntryAsyncTask.TestEntryCreatedListener {

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private TextView text;
    private EditText nameEdit;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        nameEdit = (EditText) findViewById(R.id.entry_name);
        addButton = (Button) findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTestEntry();
            }
        });

        reload();
    }

    private void reload() {
        new GetTestCollectionAsyncTask(this).execute();
    }

    @Override
    public void onDownloaded(List<TestEntry> entries) {
        if (entries == null) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder builder = new StringBuilder();

        for (TestEntry entry : entries) {
            builder.append(entry.getName())
                    .append(System.lineSeparator());
        }

        text.setText(builder.toString());
    }

    private void addTestEntry() {
        String name = nameEdit.getText().toString();
        TestEntry testEntry = new TestEntry(name);
        new CreateTestEntryAsyncTask(this).execute(testEntry);
    }

    @Override
    public void entryCreated(boolean successful) {
        Toast.makeText(this, "created: "+successful, Toast.LENGTH_SHORT).show();

        if(successful) {
            reload();
        }

    }
}
