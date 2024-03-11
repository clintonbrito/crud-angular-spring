import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from '@angular/router';
import { of } from 'rxjs';

import { CoursesService } from '../services/courses.service';
import { Course } from './../model/course';

export const courseResolver: ResolveFn<Course> =
  (route: ActivatedRouteSnapshot, _state: RouterStateSnapshot) => {
    if(route.params['id']) {
      return inject(CoursesService).loadById(route.paramMap.get('id')!);
    }
    return of({ _id: '', name: '', category: '', lessons: [] });
  }
