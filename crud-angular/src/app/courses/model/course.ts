import { Lesson } from "./lesson";

export interface Course {
  _id: string; // o id com _ (underscore) junto é porque o MongoDB usa dessa forma, daí fica mais "genérico" e fica mais difícil saber qual é o banco de dados que a aplicação está usando.
  name: string;
  category: string;
  lessons: Lesson[];
}
