import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { map } from 'rxjs/operators';

@Component({
  selector: 'app-route-example',
  templateUrl: './route-example.component.html',
  styleUrls: ['./route-example.component.css']
})
export class RouteExampleComponent {
  public tech: any;

  constructor(route: ActivatedRoute) {
    this.tech = route.data.pipe(map(data => data.technology));
  }
}
