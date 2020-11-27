import {
  Component,
  ComponentFactory,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef,
} from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { RecipesComponent } from './recipies/recipes.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  @ViewChild(HeaderComponent) navbar: HeaderComponent;
  @ViewChild('container', { read: ViewContainerRef, static: true }) container;

  constructor(private resolver: ComponentFactoryResolver) {}
  ngOnInit() {
    this.renderView(RecipesComponent);
  }

  renderView(view: any) {
    this.container.clear();
    const factory: ComponentFactory = this.resolver.resolveComponentFactory(
      view
    );
    this.container.createComponent(factory);
  }
}
