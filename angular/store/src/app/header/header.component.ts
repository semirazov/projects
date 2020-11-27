import {
  Component,
  ElementRef,
  EventEmitter,
  OnInit,
  Output,
} from '@angular/core';
import { RecipesComponent } from '../recipies/recipes.component';
import { ShoppingListComponent } from '../shopping-list/shopping-list.component';

enum ComponentView {
  SHOPPING,
  RECIPES,
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  private toggleButton: any;
  private sidebarVisible: boolean;

  private componentViewType = ComponentView;
  private componentViews: { [key in ComponentView]: any } = {
    [ComponentView.SHOPPING]: ShoppingListComponent,
    [ComponentView.RECIPES]: RecipesComponent,
  };
  @Output() currentView: EventEmitter<any> = new EventEmitter<any>();

  constructor(private element: ElementRef) {
    this.sidebarVisible = false;
  }

  ngOnInit() {
    const navbar: HTMLElement = this.element.nativeElement;
    this.toggleButton = navbar.getElementsByClassName('navbar-toggler')[0];
  }
  sidebarOpen() {
    const toggleButton = this.toggleButton;
    const html = document.getElementsByTagName('html')[0];
    setTimeout(function () {
      toggleButton.classList.add('toggled');
    }, 500);
    html.classList.add('nav-open');

    this.sidebarVisible = true;
  }
  sidebarClose() {
    const html = document.getElementsByTagName('html')[0];
    // console.log(html);
    this.toggleButton.classList.remove('toggled');
    this.sidebarVisible = false;
    html.classList.remove('nav-open');
  }
  sidebarToggle() {
    // const toggleButton = this.toggleButton;
    // const body = document.getElementsByTagName('body')[0];
    if (this.sidebarVisible === false) {
      this.sidebarOpen();
    } else {
      this.sidebarClose();
    }
  }

  changeView(event: Event, view: ComponentView) {
    event.preventDefault();
    this.currentView.emit(this.componentViews[view]);
  }
}
