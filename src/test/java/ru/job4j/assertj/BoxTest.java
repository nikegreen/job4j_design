package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphereName() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).hasSizeGreaterThan(0).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedronName() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).hasSizeGreaterThan(0).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCubeName() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).hasSizeGreaterThan(0).isEqualTo("Cube");
    }

    @Test
    void isThisUnknownName() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).hasSizeGreaterThan(0).isEqualTo("Unknown object");
    }

    @Test
    void isThisSphereNumberOfVertices() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isGreaterThan(-1).isLessThan(1).isEqualTo(0);
    }

    @Test
    void isThisTetrahedronNumberOfVertices() {
        Box box = new Box(4, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isGreaterThan(3).isLessThan(5).isEqualTo(4);
    }

    @Test
    void isThisCubeNumberOfVertices() {
        Box box = new Box(8, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isGreaterThan(7).isLessThan(9).isEqualTo(8);
    }

    @Test
    void isThisUnknownNumberOfVertices() {
        Box box = new Box(1, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isGreaterThan(-2).isLessThan(0).isEqualTo(-1);
    }

    @Test
    void isThisSphereExist() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue().isEqualTo(true);
    }

    @Test
    void isThisTetrahedronExist() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue().isEqualTo(true);
    }

    @Test
    void isThisCubeExist() {
        Box box = new Box(8, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue().isEqualTo(true);
    }

    @Test
    void isThisUnknownExist() {
        Box box = new Box(1, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse().isEqualTo(false);
    }

    @Test
    void isThisSphereArea() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isGreaterThan(1256)
                .isLessThan(1257)
                .isEqualTo(1256.637, withPrecision(0.006d))
                .isCloseTo(1256.637d, Percentage.withPercentage(0.01d));
    }

    @Test
    void isThisTetrahedronArea() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area).isGreaterThan(173)
                .isLessThan(174)
                .isEqualTo(173.2d, withPrecision(0.06d))
                .isCloseTo(173.2d, Percentage.withPercentage(0.01d));
    }

    @Test
    void isThisCubeArea() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isGreaterThan(599)
                .isLessThan(601)
                .isEqualTo(600, withPrecision(0.006d))
                .isCloseTo(600.0d, Percentage.withPercentage(1.0d));
    }

    @Test
    void isThisUnknownArea() {
        Box box = new Box(1, 10);
        double area = box.getArea();
        assertThat(area).isGreaterThan(-1)
                .isLessThan(1)
                .isEqualTo(0, withPrecision(0.006d))
                .isCloseTo(0.0d, Percentage.withPercentage(1.0d));
    }
}
