package subway.line.section;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lines/{lineId}/sections")
@RequiredArgsConstructor
public class LineSectionController {

  private final LineSectionService lineSectionService;

  @GetMapping
  public ResponseEntity<List<LineSectionResponse>> showSections(@PathVariable Long lineId) {
    return ResponseEntity.ok(lineSectionService.getAllSectionsOfLine(lineId));
  }

  @PostMapping
  public ResponseEntity<LineSectionResponse> createSection(@PathVariable Long lineId, @RequestBody LineSectionRequest request) {
    LineSectionResponse response = LineSectionResponse.fromEntity(lineSectionService.createSection(lineId, request));

    final String uri = String.format("/lines/%s/sections/%s", response.getLineId(), response.getSectionId());
    return ResponseEntity.created(URI.create(uri))
        .body(response);
  }

  @DeleteMapping
  void deleteMapping() {

  }
}
