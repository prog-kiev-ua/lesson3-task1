package ua.kovalev;

import ua.kovalev.exceptions.*;

import java.io.*;

public class GroupFileStorage {
    private File workFolder;

    public GroupFileStorage() {
        super();
        this.workFolder = new File("группы");
        if (!workFolder.exists()) this.workFolder.mkdir();
    }

    public GroupFileStorage(File workFolder) {
        super();
        this.workFolder = workFolder;
    }

    public void saveGroupToCSV(Group group) throws SaveGroupToCSVException, IOException {
        group.sortStudentsBySurname();
        File file = new File(this.workFolder, group.getName());

        // удаляю файл с группой если такой есть
        if (file.exists())
            throw new SaveGroupToCSVException("Файл с таким названием группы уже существует");

        if (!file.exists() && !file.createNewFile())
            throw new IOException("Ошибка создания файла");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(file))) {
            Student[] students = group.getBaseStudents();
            for (int i = 0; i < group.getCountStudents(); i++) {
                bufferedWriter.write(students[i].toCSVString());
                // если есть следующий элемент делаю перенос строки
                if (i < (group.getCountStudents() - 1))
                    bufferedWriter.newLine();
            }
        }
    }

    public Group loadGroupFromCSV(File file) throws IOException, AddStudentException, LoadGroupFromCSVException {

        Group group = new Group(file.getName());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                Student st = new Student().fromCSVString(line);
                ServiceStudent.addStudentToGroup(st, group);
            }
        }

        if (group == null)
            throw new LoadGroupFromCSVException("Группа не создана");

        return group;
    }

    public File findFileByGroupName(String groupName, File workFolder) throws NoSuchFolderException, NoSuchGroupException {
        if (!workFolder.isDirectory())
            throw new NoSuchFolderException("Указанный каталог не найден");

        File findedGroup = null;
        for (File file : workFolder.listFiles()) {
            if (file.getName().toUpperCase().equals(groupName.toUpperCase())) {
                findedGroup = file;
                break;
            }
        }

        if (findedGroup == null)
            throw new NoSuchGroupException("Группа не найдена");

        return findedGroup;
    }

    public File getWorkFolder() {
        return workFolder;
    }

    public void setWorkFolder(File workFolder) {
        this.workFolder = workFolder;
    }
}
