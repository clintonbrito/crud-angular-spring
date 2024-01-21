import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Course } from '../model/course';
import { CoursesService } from '../services/courses.service';

@Injectable()
export class CourseResolver {
  constructor(private coursesService: CoursesService) { }

  resolve(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<Course> {
    if (route.params['id']) {
      return this.coursesService.findById(route.params['id']);
    }

    return of({ _id: '', name: '', category: '' });
  };
}

// @Injectable({
//   providedIn: 'root'
// })

// export class CourseResolver {

//   constructor(private coursesService: CoursesService) { }

//     resolve(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<Course> {

//     if (route.params['id']) {
//       return this.coursesService.findById(route.params['id']);
//     }

//     return of({ _id: '', nome: '', categoria: '' });
//   };

// }
