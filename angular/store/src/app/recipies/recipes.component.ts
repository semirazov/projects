import { Component, OnInit } from '@angular/core';
import { Recipe } from './recipe';

@Component({
  selector: 'app-recipies',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css'],
})
export class RecipesComponent implements OnInit {
  private selectedRecipe: Recipe;
  constructor() {}

  ngOnInit(): void {}

  onRecipeSelected(recipe: Recipe) {
    this.selectedRecipe = recipe;
  }
}
