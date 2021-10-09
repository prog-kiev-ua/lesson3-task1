package ua.kovalev.executables;

import org.junit.jupiter.api.function.Executable;
import ua.kovalev.GroupFileStorage;
import ua.kovalev.exceptions.NoSuchFolderException;
import ua.kovalev.exceptions.NoSuchGroupException;

import java.io.File;
import java.io.IOException;

public class TestNoSuchFolderExceptionExecutable implements Executable {

    @Override
    public void execute() throws Throwable {
        File file = null;
        GroupFileStorage groupFileStorage = new GroupFileStorage();
        try {
            file = new File("группа_файл");
            file.createNewFile();
            groupFileStorage.findFileByGroupName("МАТЕМАТИКИ", file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFolderException e) {
            throw new NoSuchFolderException();
        } catch (NoSuchGroupException e) {
            e.printStackTrace();
        }finally {
            if (file.exists())
                file.delete();
            if(groupFileStorage.getWorkFolder().exists())
                groupFileStorage.getWorkFolder().delete();
        }
    }
}
