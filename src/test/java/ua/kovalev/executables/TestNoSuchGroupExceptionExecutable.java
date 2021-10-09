package ua.kovalev.executables;

import org.junit.jupiter.api.function.Executable;
import ua.kovalev.GroupFileStorage;
import ua.kovalev.exceptions.NoSuchFolderException;
import ua.kovalev.exceptions.NoSuchGroupException;

import java.io.File;

public class TestNoSuchGroupExceptionExecutable implements Executable {
    private File workFolder;
    private String groupName;

    public TestNoSuchGroupExceptionExecutable() {
        super();
    }

    public TestNoSuchGroupExceptionExecutable(File workFolder, String groupName) {
        super();
        this.workFolder = workFolder;
        this.groupName = groupName;
    }

    @Override
    public void execute() throws Throwable {
        GroupFileStorage groupFileStorage = new GroupFileStorage(workFolder);
        try {
            groupFileStorage.findFileByGroupName(groupName, this.workFolder);
        } catch (NoSuchFolderException e) {
            e.printStackTrace();
        } catch (NoSuchGroupException e) {
            throw new NoSuchGroupException();
        }
    }
}
