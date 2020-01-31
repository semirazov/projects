package main

import (
	"fmt"
	"math"
)

const precision float64 = 1e13
func floorToPrecision(x float64) float64 {
	return math.Floor((x * precision)) / precision
}



func (cat Cat) call (){

}

func Sqrt(x float64) float64 {
	z := x/2
	prev := 0.0
	counter := 0
	for ; floorToPrecision(z) != floorToPrecision(prev) && counter < 10; counter++ {
		prev = z
		z -= (z*z - x) / (2 * z)
		fmt.Println("Intermediate result: ", z)
	}
	fmt.Println("Steps: ", counter)
	return z;
}

func main() {
	number :=12.0
	fmt.Println("My method:   ", Sqrt(number))
	fmt.Println("Math method: ", math.Sqrt(number))
	cat := Cat{name:"Vaska"}

	say := cat.Say()

	fmt.Println(say)

}

type Talkable interface {
	Say() string
}

type Cat struct {
	name string
	age int
}

func (cat Cat) Say() string {
	return fmt.Sprint(cat.name)
}