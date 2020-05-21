import { File } from './file';

export interface Snippet {
    id: number;
    title: string;
    isPublic: boolean;
    createTime: string;
    userId: number;
    files: File[];
}
