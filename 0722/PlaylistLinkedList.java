public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        this.head = null;
    }

    public boolean contains(String songId) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songId.equals(songId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void addSong(String songId, String songName) {
        if (contains(songId)) {
            System.out.println("新增失敗：歌曲代碼 " + songId + " 已存在，代碼不可重複。");
            return;
        }

        PlaylistNode newNode = new PlaylistNode(songId, songName);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("成功新增歌曲：[" + songId + "] " + songName);
    }

    public void searchSong(String songId) {
        if (head == null) {
            System.out.println("搜尋失敗：播放清單目前為空。");
            return;
        }

        PlaylistNode current = head;
        int position = 1;
        while (current != null) {
            if (current.songId.equals(songId)) {
                System.out.println("找到歌曲 -> 代碼：" + current.songId + "，名稱：" + current.songName + "，位於第 " + position + " 首。");
                return;
            }
            current = current.next;
            position++;
        }
        System.out.println("找不到歌曲：代碼 " + songId + " 不存在於播放清單中。");
    }

    public void removeSong(String songId) {
        if (head == null) {
            System.out.println("刪除失敗：播放清單目前為空。");
            return;
        }

        if (head.songId.equals(songId)) {
            System.out.println("成功刪除頭節點歌曲：[" + head.songId + "] " + head.songName);
            head = head.next;
            return;
        }

        PlaylistNode current = head;
        while (current.next != null && !current.next.songId.equals(songId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("刪除失敗：找不到代碼為 " + songId + " 的歌曲。");
            return;
        }

        System.out.println("成功刪除歌曲：[" + current.next.songId + "] " + current.next.songName);
        current.next = current.next.next;
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("播放清單內容：(目前為空)");
            return;
        }

        System.out.println("=== 目前播放清單順序 ===");
        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". [" + current.songId + "] " + current.songName);
            current = current.next;
            index++;
        }
        System.out.println("========================");
    }
}