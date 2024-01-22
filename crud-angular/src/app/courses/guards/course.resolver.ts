import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { of } from 'rxjs';

import { CoursesService } from '../services/courses.service';
import { Course } from './../model/course';

export const courseResolver: ResolveFn<Course> =
  (route: ActivatedRouteSnapshot, _state: RouterStateSnapshot) => {
    if(route.params['id']) {
      return inject(CoursesService).findById(route.paramMap.get('id')!);
    }
    return of({ _id: '', name: '', category: '' });
  }

// @Injectable()
// export class CourseResolver {
//   constructor(private coursesService: CoursesService) { }

//   resolve(route: ActivatedRouteSnapshot, _state: RouterStateSnapshot): Observable<Course> {
//     if (route.params['id']) {
//       return this.coursesService.findById(route.params['id']);
//     }

//     return of({ _id: '', name: '', category: '' });
//   };
// }

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
