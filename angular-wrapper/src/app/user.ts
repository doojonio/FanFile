import { Snippet } from './snippet';
export interface User {
  id: number;
  login: string;
  snippetAmount: number;
  snippets: Snippet[];
}

export const Anonymous : User = {
  id: 1,
  login: 'Anonymous',
  snippetAmount: 0,
  snippets: [],
}
