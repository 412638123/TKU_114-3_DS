public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 1. 測試空清單操作 ===");
        playlist.displayPlaylist();
        playlist.searchSong("S001");
        playlist.removeSong("S001");

        System.out.println("\n=== 2. 測試新增歌曲 (含重複檢查) ===");
        playlist.addSong("S001", "晴天");
        playlist.addSong("S002", "七里香");
        playlist.addSong("S003", "夜曲");
        playlist.addSong("S004", "青花瓷");
        playlist.addSong("S002", "重複代碼測試"); // 測試重複
        playlist.displayPlaylist();

        System.out.println("\n=== 3. 測試依代碼搜尋 ===");
        playlist.searchSong("S003");
        playlist.searchSong("S999");

        System.out.println("\n=== 4. 測試刪除第一首 (Head) ===");
        playlist.removeSong("S001");
        playlist.displayPlaylist();

        System.out.println("\n=== 5. 測試刪除中間歌曲 ===");
        playlist.removeSong("S003");
        playlist.displayPlaylist();

        System.out.println("\n=== 6. 測試刪除最後一首歌曲 ===");
        playlist.removeSong("S004");
        playlist.displayPlaylist();

        System.out.println("\n=== 7. 測試刪除找不到的歌曲 ===");
        playlist.removeSong("S888");
        playlist.displayPlaylist();
    }
}