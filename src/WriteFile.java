import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class WriteFile {
    static void writeFile(String filePath) {

        try {
            ParsingXml parsingXml = new ParsingXml("파싱할 xml");
            ArrayList<String[]> parsingData = parsingXml.getCORPCODE();

            File file = new File(filePath);
            if (!file.exists()) {
                // 파일이 존재하지 않는다면 생성
                file.createNewFile();
            } else {
                // 파일이 존재한다면 삭제하고 생성
                file.delete();
                file.createNewFile();
            }

            // BufferedWriter 생성
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            // 파일을 쓸때, 구분자는 |로 해줌
            // 엑셀에서 읽을 때 편하게 하기 위함
            for (String[] strArr : parsingData) {
                writer.write(strArr[0] + "|" + strArr[1] + "|" + strArr[2]);
                writer.newLine();
            }

            // 버퍼 및 스트림 뒷정리
            writer.flush(); // 버퍼의 남은 데이터를 모두 쓰기
            writer.close(); // 스트림 종료

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
