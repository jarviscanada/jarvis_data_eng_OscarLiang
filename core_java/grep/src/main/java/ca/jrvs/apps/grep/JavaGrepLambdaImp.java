package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error(ex.getMessage(), ex);
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<>();
    File dir = new File(rootDir);
    File[] dirFiles = dir.listFiles();
    if (dirFiles == null) {
      // Return empty list if given String is not a directory
      return files;
    }
    Arrays.asList(dirFiles).stream().forEach(file -> {
      if (file.isDirectory()) {
        files.addAll(listFiles(file.getAbsolutePath()));
      } else {
        files.add(file);
      }
    });
    return files;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> lines = new ArrayList<String>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      lines = br.lines().collect(Collectors.toList());
      br.close();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("Input argument is not a file.");
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex);
    }
    return lines;
  }
}
