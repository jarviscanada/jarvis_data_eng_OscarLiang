package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<String>();
    List<File> allFiles = listFiles(this.getRootPath());
    for (File f : allFiles) {
      List<String> fileLines = readLines(f);
      for (String line : fileLines) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<File>();
    File dir = new File(rootDir);
    File[] dirFiles = dir.listFiles();
    if (dirFiles == null) {
      // Return empty list if given String is not a directory
      return files;
    } else {
      for (File f : dirFiles) {
        // Recursively add files from sub-directories
        if (f.isDirectory()) {
          files.addAll(listFiles(f.getAbsolutePath()));
        } else if (f.isFile()) {
          files.add(f);
        }
      }
    }
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
      this.logger.error(ex.getMessage(), ex);
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.compile(this.getRegex()).matcher(line).matches();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File newFile = new File(this.getOutFile());
    OutputStream os = new FileOutputStream(newFile);
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os));
    for (String l : lines) {
      out.write(l + "\n");
    }
    out.close();
  }

  @Override
  public String getRootPath() {
    return this.rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
