public class ResultCalculator {

    /**
     * Заполняет total, percent и grade внутри переданного Result и возвращает его.
     */
    public static Result calculate(Result r) {
        int math = r.getMath();
        int science = r.getScience();
        int english = r.getEnglish();

        int total = math + science + english;
        double percent = total / 3.0;
        String grade = percent >= 40.0 ? "Pass" : "Fail";

        r.setTotal(total);
        r.setPercent(percent);
        r.setGrade(grade);
        return r;
    }
}
