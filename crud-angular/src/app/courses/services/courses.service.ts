import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, first } from 'rxjs';

import { Course } from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  private readonly API = '/api/courses';

  constructor(private httpClient: HttpClient) { }

  findAll() {
    return this.httpClient.get<Course[]>(this.API)
      .pipe(
        first(),
        // delay(5000),
        // tap(courses => console.log(courses))
        );
  }

  loadById(id: string): Observable<Course> {
    return this.httpClient.get<Course>(`${this.API}/${id}`);
  }

  save(data: Partial<Course>) {
    // console.log('primeiro console: ', data);

    // data._id ? this.update(data) : this.create(data);

    if (data._id) {
      // console.log('updated: ', data);
      return this.update(data);
    }

    // console.log('created: ', data);
    return this.create(data);
  }

  private create(data: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, data).pipe(first());
  }

  private update(data: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API}/${data._id}`, data).pipe(first());

  }
}
