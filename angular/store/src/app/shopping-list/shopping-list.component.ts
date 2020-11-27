import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';
import remove from 'lodash/remove';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css'],
})
export class ShoppingListComponent implements OnInit {
  selectedIngredient: Ingredient;
  ingredients: Array<Ingredient> = new Array<Ingredient>(
    new Ingredient('Apples', 5),
    new Ingredient('Tomatoes', 10),
    new Ingredient('Potato', 2)
  );

  constructor() {}

  ngOnInit(): void {}

  onIngredientCreated(ingredient: Ingredient) {
    this.ingredients.push(ingredient);
  }
  onIngredientDeleted(ingredient: Ingredient) {
    remove(this.ingredients, { name: ingredient.name });
  }
}
