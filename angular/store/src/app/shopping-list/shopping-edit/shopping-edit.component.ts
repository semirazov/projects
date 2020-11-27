import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { Ingredient } from '../../shared/ingredient.model';

@Component({
  selector: 'app-shopping-edit',
  templateUrl: './shopping-edit.component.html',
  styleUrls: ['./shopping-edit.component.css'],
})
export class ShoppingEditComponent implements OnInit {
  @Input() ingredient: Ingredient = new Ingredient('', 0);
  @ViewChild('nameInput', { static: true }) nameInput: ElementRef;
  @ViewChild('amountInput', { static: true }) amountInput: ElementRef;
  @Output() ingredientCreated: EventEmitter<Ingredient> = new EventEmitter<
    Ingredient
  >();
  @Output() ingredientDeleted: EventEmitter<Ingredient> = new EventEmitter<
    Ingredient
  >();

  constructor() {}

  ngOnInit(): void {}

  clear() {
    this.nameInput.nativeElement.value = '';
    this.amountInput.nativeElement.value = '';
  }

  create() {
    const ingredient: Ingredient = new Ingredient(
      this.nameInput.nativeElement.value,
      this.amountInput.nativeElement.value
    );

    this.ingredientCreated.emit(ingredient);
  }

  delete() {
    const ingredient: Ingredient = new Ingredient(
      this.nameInput.nativeElement.value,
      this.amountInput.nativeElement.value
    );

    this.ingredientDeleted.emit(ingredient);
  }
}
