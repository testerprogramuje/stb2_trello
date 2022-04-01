package pl.akademiaqa.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.requests.board.CreateBoardRequest;
import pl.akademiaqa.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CreateBoardTest {

    private String boardId;

    @DisplayName("Create a board with valid data")
    @ParameterizedTest(name = "Board name: {0}")
    @MethodSource("sampleBoardNameData")
    void createBoardTest(String boardName) {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        // CREATE A BOARD
        final Response response = CreateBoardRequest.createBoardRequest(queryParams);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);
        boardId = json.getString("id");

        // DELETE A BOARD
        final Response deleteResponse = DeleteBoardRequest.deleteBoardRequest(boardId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    private static Stream<Arguments> sampleBoardNameData() {

        return Stream.of(
                Arguments.of("nazwaTablicy", "aaa", "aaa"),
                Arguments.of("NAZWATABLICY"),
                Arguments.of("NAZWA_TABLICY"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&")
        );
    }
}
