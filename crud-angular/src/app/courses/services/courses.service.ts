import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';

import { Course } from '../model/course';
import { CoursePage } from '../model/course-page';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  private readonly API = '/api/courses';

  constructor(private httpClient: HttpClient) { }

  findAll(page = 0, pageSize = 5) {
    return this.httpClient.get<CoursePage>(this.API, { params: { page, pageSize } })
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

    if (data._id) {
      return this.update(data);
    }

    return this.create(data);
  }

  private create(data: Partial<Course>) {
    return this.httpClient.post<Course>(this.API, data).pipe(first());
  }

  private update(data: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API}/${data._id}`, data).pipe(first());

  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());

  }
}
