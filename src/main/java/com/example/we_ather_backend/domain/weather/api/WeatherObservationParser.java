package com.example.we_ather_backend.domain.weather.api;

import com.example.we_ather_backend.domain.weather.dto.WeatherObservationDTO;

import java.util.ArrayList;
import java.util.List;

public class WeatherObservationParser {
    // 각 줄의 데이터를 파싱하여 WeatherObservationDTO 객체로 변환
    public static WeatherObservationDTO parseLine(String line) {
        // 공백으로 데이터를 분리 (데이터 간 공백이 여러 개 있을 수 있으므로 정규표현식 사용)
        String[] data = line.trim().split("\\s+");

        // WeatherObservationDTO 객체 생성 및 데이터 설정
        WeatherObservationDTO dto = new WeatherObservationDTO();
        dto.setDate(data[0]);
        dto.setTaAvg(Double.parseDouble(data[10]));
        dto.setTaMax(Double.parseDouble(data[11]));
        dto.setTaMin(Double.parseDouble(data[13]));
        dto.setCaTot(Double.parseDouble(data[31]));
        dto.setRnDay(Double.parseDouble(data[38]));
        return dto;
    }

    // 여러 줄의 데이터를 파싱하여 List<WeatherObservationDTO>로 변환
    public static List<WeatherObservationDTO> parseData(String rawData) {
        List<WeatherObservationDTO> observations = new ArrayList<>();
        String[] lines = rawData.split("\n");

        for (String line : lines) {
            // 각 줄을 파싱하여 DTO로 변환 후 리스트에 추가
            if (!line.trim().isEmpty() && !line.startsWith("#")) { // 빈 줄이나 주석을 건너뜀
                observations.add(parseLine(line));
            }
        }

        return observations;
    }

    public static void main(String[] args) {
        String data = """
            20151211 108  3.4  2933   7  6.6 2349   7 10.6 2342   6.4  12.2 1345   3.3  512  -2.4   5.1   1.0  56.5  33.0 1841   5.2   1.9  -9.0 -9.00 1008.0 1018.6 1024.5 2349 1012.4  122  2.0  7.7  9.6 -9.0  8.33  1.54 1200   -9.0   -9.0 -9.00   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   7.4  10.1  12.6  17.2  17.7
            20151212 108  2.6  2268   7  6.4    9   9 10.0    1   5.2  11.0 1519   1.1  738  -4.7   3.8  -3.0  50.1  33.0 1526   4.3   1.1  -9.0 -9.00 1014.3 1024.9 1027.5  907 1022.9 1444  1.3  8.9  9.6 -9.0  9.32  1.61 1200   -9.0   -9.0 -9.00   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   7.4  10.1  12.5  17.2  17.6
            20151213 108  1.4  1213   2  3.3 1615   2  5.0 1732   5.2  12.2 1440   0.9  802  -4.6   4.0  -4.4  50.5  30.0 1355   4.3   1.0  -9.0 -9.00 1013.1 1023.8 1025.1  918 1022.2 1523  1.6  8.9  9.6 -9.0  8.85  1.55 1200   -9.0    0.0 -9.00   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   7.2  10.1  12.4  17.1  17.6
            20151214 108  2.2  1927   5  3.8 1749  27  5.9 2246   5.0   6.4 1214   2.6   45   0.9   4.8  -2.2  75.6  55.0   17   6.6   0.3  -9.0 -9.00 1010.2 1020.8 1023.7    3 1018.7 2148  9.8  0.0  9.6 -9.0  1.89  0.35 1000    2.5    2.6 12.45   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   -9.0   -9   7.3  10.0  12.3  17.0  17.6
        """;

        List<WeatherObservationDTO> observations = parseData(data);
        // 결과 출력
        for (WeatherObservationDTO observation : observations) {
            System.out.println(observation);
        }
    }
}
