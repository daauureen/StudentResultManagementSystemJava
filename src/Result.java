import java.util.Objects;

public class Result {
    private String name;
    private String roll;
    private int math;
    private int science;
    private int english;

    private int total;
    private double percent;
    private String grade;

    public Result(String name, String roll, int math, int science, int english) {
        this.name = name;
        this.roll = roll;
        this.math = math;
        this.science = science;
        this.english = english;
    }

    // getters / setters
    public String getName() { return name; }
    public String getRoll() { return roll; }
    public int getMath() { return math; }
    public int getScience() { return science; }
    public int getEnglish() { return english; }
    public int getTotal() { return total; }
    public double getPercent() { return percent; }
    public String getGrade() { return grade; }

    public void setTotal(int total) { this.total = total; }
    public void setPercent(double percent) { this.percent = percent; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("Name: %s, Roll: %s, Total: %d, %%: %.2f, Result: %s",
                name, roll, total, percent, grade);
    }

    // For file storage (simple CSV-ish line)
    public String toStorageLine() {
        // escape commas in name if any
        String safeName = name.replace(",", " ");
        return String.join(",",
                safeName,
                roll,
                String.valueOf(math),
                String.valueOf(science),
                String.valueOf(english),
                String.valueOf(total),
                String.format("%.2f", percent),
                grade
        );
    }

    public static Result fromStorageLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 8) return null;
        try {
            String name = parts[0];
            String roll = parts[1];
            int math = Integer.parseInt(parts[2]);
            int science = Integer.parseInt(parts[3]);
            int english = Integer.parseInt(parts[4]);
            int total = Integer.parseInt(parts[5]);
            double percent = Double.parseDouble(parts[6]);
            String grade = parts[7];

            Result r = new Result(name, roll, math, science, english);
            r.setTotal(total);
            r.setPercent(percent);
            r.setGrade(grade);
            return r;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;
        return Objects.equals(name, result.name) && Objects.equals(roll, result.roll);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, roll);
    }
}
