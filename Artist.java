import java.util.*;

class Artist {
    String name;
    long listeners;

    Artist(String n, long l) {
        name = n;
        listeners = l;
    }
}

class Codechef {

    /** Returns the top-k artists by listeners, sorted descending. */
    static List<Artist> topK(List<Artist> all, int k) {

        // Min-heap by listeners: peek is the smallest of the current top-k.
        PriorityQueue<Artist> minHeap =
            new PriorityQueue<>(
                (a, b) -> Long.compare(a.listeners, b.listeners)
            );

        // TOOL 1: scan 'all'. For each artist:
        // - if heap.size() < k, just offer.
        // - else if artist.listeners > heap.peek().listeners,
        //   poll() the smallest and offer the new one.

        for (Artist artist : all) {

            if (minHeap.size() < k) {
                minHeap.offer(artist);
            }
            else if (artist.listeners > minHeap.peek().listeners) {
                minHeap.poll();
                minHeap.offer(artist);
            }
        }

        // TOOL 2: extract heap into a list and sort descending by listeners.
        List<Artist> result = new ArrayList<>(minHeap);

        result.sort(
            (a, b) -> Long.compare(b.listeners, a.listeners)
        );

        return result;
    }

    public static void main(String[] args) {

        List<Artist> all = Arrays.asList(
            new Artist("Artist1", 45),
            new Artist("Artist2", 12),
            new Artist("Artist3", 78),
            new Artist("Artist4", 23),
            new Artist("Artist5", 56),
            new Artist("Artist6", 89),
            new Artist("Artist7", 34),
            new Artist("Artist8", 67),
            new Artist("Artist9", 18),
            new Artist("Artist10", 91),
            new Artist("Artist11", 50),
            new Artist("Artist12", 39)
        );

        List<Artist> ans = topK(all, 5);

        for (Artist a : ans) {
            System.out.println(a.name + " " + a.listeners);
        }
    }
}