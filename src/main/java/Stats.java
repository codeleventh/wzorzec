import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Stats extends HashMap<LocalDate, Integer> {

    private Integer min = 0, max = 0;

    Stats() {
        super();
    }

    Stats(String login, Integer year) throws IOException {
        super();

        String url = "https://github.com/" + login + "?tab=overview&from=" + year + "-12-01&to=" + year + "-12-31";
        Elements elements = Jsoup.connect(url).get().select("rect.day");
        if (elements.size() == 0) {
            throw new IOException("no commit stats in profile");
        }

        Integer min = Integer.MAX_VALUE, max = 0;
        for (Element e : elements) {
            LocalDate date = LocalDate.parse(e.attr("data-date"));
            Integer count = Integer.parseInt(e.attr("data-count"));
            this.put(date, count);
        }
    }

    @Override
    public Integer put(LocalDate key, Integer value) {
        if (value == 0) {
            return null;
        }
        if (value > max) {
            max = value;
        } else if (value > 0 && (min == 0 || value < min)) {
            min = value;
        }
        return super.put(key, value);
    }

    @Override
    public Integer remove(Object key) {
        if (this.get(key) <= 0) {
            return super.remove(key);
        } else {
            return null; // just in case, чтобы не ломать min и max
        }
    }

    public void multiplyStats(double mul) {
        for (Entry<LocalDate, Integer> e : this.entrySet()) {
            e.setValue((int) Math.floor(e.getValue() * mul));
        }
    }

    public void substractStats(Stats stat) {
        for (Entry<LocalDate, Integer> e : this.entrySet()) {
            if (stat.containsKey(e.getKey())) {
                Integer i = e.getValue() - stat.get(e.getKey());
                e.setValue(i);
            }
        }
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }
}