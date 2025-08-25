package com.flab.blackfriday.games;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class GameController {

    // 메인 페이지
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String mainPage() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Unity Game Platform</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        margin: 0;
                        padding: 0;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        min-height: 100vh;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                    }
                    .container {
                        text-align: center;
                        background: rgba(255, 255, 255, 0.95);
                        padding: 50px;
                        border-radius: 20px;
                        box-shadow: 0 25px 50px rgba(0,0,0,0.2);
                        backdrop-filter: blur(10px);
                        max-width: 500px;
                    }
                    h1 {
                        background: linear-gradient(45deg, #667eea, #764ba2);
                        -webkit-background-clip: text;
                        -webkit-text-fill-color: transparent;
                        background-clip: text;
                        font-size: 3em;
                        margin-bottom: 20px;
                        animation: pulse 2s infinite;
                    }
                    @keyframes pulse {
                        0%, 100% { transform: scale(1); }
                        50% { transform: scale(1.05); }
                    }
                    .btn {
                        display: inline-block;
                        background: linear-gradient(45deg, #667eea, #764ba2);
                        color: white;
                        padding: 15px 30px;
                        text-decoration: none;
                        border-radius: 30px;
                        font-weight: 600;
                        margin: 15px 10px;
                        transition: all 0.3s ease;
                        box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
                    }
                    .btn:hover {
                        transform: translateY(-3px);
                        box-shadow: 0 8px 25px rgba(102, 126, 234, 0.5);
                    }
                    .description {
                        color: #666;
                        font-size: 1.1em;
                        margin: 20px 0;
                        line-height: 1.6;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>🎮 Game Platform</h1>
                    <div class="description">
                        Unity 게임 배포 플랫폼에 오신 것을 환영합니다!<br>
                        ECS Fargate + CodePipeline으로 구동되는<br>
                        현대적인 게임 배포 서비스입니다.
                    </div>
                    <a href="/games" class="btn">🎯 게임 다운로드</a>
                    <a href="/api/games" class="btn">📋 API 문서</a>
                </div>
            </body>
            </html>
            """;
    }

    // 게임 다운로드 페이지
    @GetMapping(value = "/games", produces = MediaType.TEXT_HTML_VALUE)
    public String gamesPage() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Game Downloads</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        margin: 0;
                        padding: 20px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        min-height: 100vh;
                        color: #333;
                    }
                    .container {
                        max-width: 900px;
                        margin: 0 auto;
                        background: rgba(255, 255, 255, 0.95);
                        border-radius: 20px;
                        padding: 40px;
                        box-shadow: 0 25px 50px rgba(0,0,0,0.2);
                        backdrop-filter: blur(10px);
                    }
                    .header {
                        text-align: center;
                        margin-bottom: 40px;
                    }
                    .header h1 {
                        background: linear-gradient(45deg, #667eea, #764ba2);
                        -webkit-background-clip: text;
                        -webkit-text-fill-color: transparent;
                        background-clip: text;
                        font-size: 2.5em;
                        margin-bottom: 10px;
                        animation: slideIn 1s ease-out;
                    }
                    @keyframes slideIn {
                        from { opacity: 0; transform: translateY(-30px); }
                        to { opacity: 1; transform: translateY(0); }
                    }
                    .game-grid {
                        display: grid;
                        gap: 25px;
                        margin: 30px 0;
                    }
                    .game-card {
                        background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
                        border-radius: 15px;
                        padding: 25px;
                        border-left: 5px solid #667eea;
                        transition: all 0.3s ease;
                        position: relative;
                        overflow: hidden;
                    }
                    .game-card::before {
                        content: '';
                        position: absolute;
                        top: 0;
                        left: -100%;
                        width: 100%;
                        height: 100%;
                        background: linear-gradient(90deg, transparent, rgba(255,255,255,0.3), transparent);
                        transition: left 0.6s ease;
                    }
                    .game-card:hover {
                        transform: translateY(-5px) scale(1.02);
                        box-shadow: 0 15px 35px rgba(102, 126, 234, 0.2);
                        border-left-color: #764ba2;
                    }
                    .game-card:hover::before {
                        left: 100%;
                    }
                    .game-title {
                        font-size: 1.5em;
                        font-weight: bold;
                        margin-bottom: 15px;
                        color: #333;
                        display: flex;
                        align-items: center;
                        gap: 10px;
                    }
                    .game-description {
                        color: #666;
                        margin-bottom: 20px;
                        line-height: 1.7;
                        font-size: 1.05em;
                    }
                    .game-meta {
                        display: flex;
                        gap: 20px;
                        margin-bottom: 20px;
                        font-size: 0.9em;
                        color: #777;
                    }
                    .meta-item {
                        background: rgba(102, 126, 234, 0.1);
                        padding: 5px 12px;
                        border-radius: 15px;
                        border: 1px solid rgba(102, 126, 234, 0.2);
                    }
                    .download-btn {
                        background: linear-gradient(45deg, #667eea, #764ba2);
                        color: white;
                        border: none;
                        padding: 12px 25px;
                        border-radius: 25px;
                        cursor: pointer;
                        font-weight: 600;
                        transition: all 0.3s ease;
                        text-decoration: none;
                        display: inline-flex;
                        align-items: center;
                        gap: 8px;
                        font-size: 1em;
                        position: relative;
                        z-index: 1;
                    }
                    .download-btn:hover {
                        transform: translateY(-2px);
                        box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
                    }
                    .download-btn:active {
                        transform: translateY(0);
                    }
                    .test-notice {
                        background: linear-gradient(135deg, #fff3cd, #ffeaa7);
                        border: 2px solid #f39c12;
                        border-radius: 10px;
                        padding: 20px;
                        margin-bottom: 30px;
                        text-align: center;
                    }
                    .test-notice h3 {
                        margin-top: 0;
                        color: #d68910;
                    }
                    .back-btn {
                        display: inline-block;
                        background: rgba(102, 126, 234, 0.1);
                        color: #667eea;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 20px;
                        font-weight: 500;
                        margin-bottom: 20px;
                        transition: all 0.3s ease;
                        border: 2px solid rgba(102, 126, 234, 0.2);
                    }
                    .back-btn:hover {
                        background: rgba(102, 126, 234, 0.2);
                        transform: translateX(-3px);
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <a href="/" class="back-btn">← 홈으로 돌아가기</a>
                    
                    <div class="header">
                        <h1>🎮 Unity Game Downloads</h1>
                        <p>고품질 Unity 게임을 다운로드하고 즐겨보세요!</p>
                    </div>
                    
                    <div class="test-notice">
                        <h3>🧪 주의사항</h3>
                        <p>게임들은 전부 완성되지 않은 프로토타입입니다. 완성되지 않은 기능과 버그가 있습니다. ㅎㅎ ㅈㅅ;</p>
                    </div>
                    
                    <div class="game-grid">
                        <div class="game-card">
                            <div class="game-title">
                                📇 Card RPG
                            </div>
                            <div class="game-description">
                                2D 월드에서 인공지능과 1대1로 싸우는 게임입니다.
                                카드를 사용해서 본인만의 스타일대로 싸워 보세요.
                            </div>
                            <div class="game-meta">
                                <span class="meta-item">📦 Size: 44.7MB</span>
                                <span class="meta-item">🏷️ Version: 0.0.6</span>
                                <span class="meta-item">💻 Platform: Windows</span>
                            </div>
                            <a href="/api/games/download/CRPG" class="download-btn" onclick="handleDownload(this, 'CRPG')">
                                ⬇️ Download Game
                            </a>
                        </div>
                        
                        <div class="game-card">
                            <div class="game-title">
                                🗡️ Eden Dev
                            </div>
                            <div class="game-description">
                                카타나 하나로 이뤄지는 공방일체
                                적의 공격을 쳐내고 처치하세요.
                            </div>
                            <div class="game-meta">
                                <span class="meta-item">📦 Size: 169MB</span>
                                <span class="meta-item">🏷️ Version: 0.2.1</span>
                                <span class="meta-item">💻 Platform: Windows</span>
                            </div>
                            <a href="/api/games/download/EdenDev" class="download-btn" onclick="handleDownload(this, 'EdenDev')">
                                ⬇️ Download Game
                            </a>
                        </div>
                    </div>
                </div>
                
                <script>
                    function handleDownload(btn, gameId) {
                        // 다운로드 통계 기록
                        fetch(`/api/games/${gameId}/download-stats`, {
                            method: 'POST'
                        }).catch(err => console.log('Stats recording failed:', err));
                        
                        // 버튼 상태 변경
                        const originalText = btn.innerHTML;
                        btn.innerHTML = '⏳ Downloading...';
                        btn.style.pointerEvents = 'none';
                        btn.style.opacity = '0.7';
                        
                        setTimeout(() => {
                            btn.innerHTML = '✅ Downloaded!';
                            setTimeout(() => {
                                btn.innerHTML = originalText;
                                btn.style.pointerEvents = 'auto';
                                btn.style.opacity = '1';
                            }, 2000);
                        }, 1000);
                    }
                </script>
            </body>
            </html>
            """;
    }

    // REST API - 게임 목록
    @GetMapping("/api/games")
    public ResponseEntity<Map<String, Object>> getGames() {
        Map<String, Object> response = new HashMap<>();

        List<Map<String, String>> games = Arrays.asList(
                Map.of(
                        "id", "CRPG",
                        "name", "Card RPG",
                        "description", "2D 월드에서 무기와 카드를 사용하여 인공지능과 1대1 교전 데모 게임",
                        "size", "44.7MB",
                        "version", "0.0.6",
                        "platform", "Windows",
                        "downloadUrl", "/api/games/download/CRPG"
                ),
                Map.of(
                        "id", "EdenDev",
                        "name", "Eden Dev",
                        "description", "카타나 하나로 공격을 쳐내며 교전하는 어드벤처 데모 게임",
                        "size", "169MB",
                        "version", "0.2.1",
                        "platform", "Windows",
                        "downloadUrl", "/api/games/download/EdenDev"
                )
        );

        response.put("games", games);
        response.put("totalCount", games.size());
        response.put("status", "success");

        return ResponseEntity.ok(response);
    }

    // 게임 다운로드
    @GetMapping("/api/games/download/{gameId}")
    public ResponseEntity<Resource> downloadGame(@PathVariable String gameId) {
        try {
            if (!isValidGameId(gameId)) {
                return ResponseEntity.badRequest().build();
            }


            String filePath = "games/" + gameId + ".zip";
            Resource resource = new ClassPathResource(filePath);

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // 실제 게임은 .zip으로, 테스트는 .txt로 다운로드
            String fileName = gameId + ".zip";
            String contentType = "text/plain";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileName + "\"")
                    .header("X-Game-Name", gameId)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 다운로드 통계
    @PostMapping("/api/games/{gameId}/download-stats")
    public ResponseEntity<Map<String, String>> recordDownload(@PathVariable String gameId) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "recorded");
        response.put("gameId", gameId);
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));

        System.out.println("Download recorded: " + gameId + " at " + System.currentTimeMillis());

        return ResponseEntity.ok(response);
    }

    private boolean isValidGameId(String gameId) {
        return gameId != null &&
                gameId.matches("^[a-zA-Z0-9_-]+$") &&
                gameId.length() <= 50;
    }
}